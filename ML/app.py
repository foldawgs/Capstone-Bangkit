from flask import Flask, request, jsonify
import numpy as np
import tensorflow as tf
from tensorflow.keras.preprocessing.sequence import pad_sequences
import joblib
import pandas as pd
from text_normalize import normalize

# Initialize Flask app
app = Flask(__name__)

# Load model, tokenizer, and dataset
MODEL_PATH = r"C:\Users\naufa\OneDrive\Dokumen\CAPSTONE\Bangkit Capstone\PROJECT\model\model.tflite"
TOKENIZER_PATH = r"C:\Users\naufa\OneDrive\Dokumen\CAPSTONE\Bangkit Capstone\PROJECT\model\tokenizer.pkl"
MOVIE_DATASET_PATH = r"C:\Users\naufa\OneDrive\Dokumen\CAPSTONE\Bangkit Capstone\PROJECT\model\fix_dataset.pkl"

# Load TFLite model
interpreter = tf.lite.Interpreter(model_path=MODEL_PATH)
interpreter.allocate_tensors()
input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

# Load tokenizer
tokenizer = joblib.load(TOKENIZER_PATH)

# Load movie dataset
df = joblib.load(MOVIE_DATASET_PATH)

# Emotion map
EMOTION_MAP = {
    0: 'sadness', 
    1: 'joy', 
    2: 'love', 
    3: 'anger', 
    4: 'fear', 
    5: 'surprise'
}

# Prediction function
def predict_emotion(user_input):
    # Normalize and tokenize input
    normalized_text = normalize(user_input)
    sequences = tokenizer.texts_to_sequences([normalized_text])
    padded_sequence = pad_sequences(sequences, maxlen=45)
    
    # Prepare input tensor
    input_data = np.array(padded_sequence, dtype=np.float32)
    interpreter.set_tensor(input_details[0]['index'], input_data)
    interpreter.invoke()
    
    # Get prediction
    emotion_probabilities = interpreter.get_tensor(output_details[0]['index'])
    predicted_class = np.argmax(emotion_probabilities, axis=1)[0]
    return EMOTION_MAP[predicted_class], predicted_class

# Recommendation function
def recommend_movies(emotion_class, top_n=15):
    # Filter and sort movies based on predicted emotion and popularity
    filtered_df = df[df['predicted_emotion'] == emotion_class]
    recommended_movies = filtered_df.sort_values(by='popularity', ascending=False).head(top_n)
    return recommended_movies[['title', 'popularity']].to_dict(orient='records')

# Define API route for prediction and recommendation
@app.route('/predict', methods=['POST'])
def predict():
    try:
        # Parse user input
        user_input = request.json.get('text', '')
        if not user_input:
            return jsonify({"error": "No text input provided"}), 400
        
        # Predict emotion
        predicted_emotion, emotion_class = predict_emotion(user_input)
        
        # Get movie recommendations
        recommendations = recommend_movies(emotion_class)
        
        # Return result
        return jsonify({
            "predicted_emotion": predicted_emotion,
            "recommendations": recommendations
        })
    except Exception as e:
        return jsonify({"error": str(e)}), 500

# Run Flask app
if __name__ == '__main__':
    app.run(debug=True)
