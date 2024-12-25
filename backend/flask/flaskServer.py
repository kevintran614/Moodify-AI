from flask import Flask, request, jsonify
from flask_cors import CORS

import model as mdl

app = Flask(__name__)
CORS(app)

@app.route('/health-check', methods=['GET'])
def health_check():
    return jsonify({"msg": "good"}), 200

@app.route('/generate-emotion-from-text', methods=['POST'])
def generate_emotion_from_text():
    etc = mdl.Model()
    data = request.get_json()
    text = data.get('text', '')
    emotion = etc.classifier(text)

    return jsonify({'emotion': emotion})

if __name__ == '__main__':
    app.run(debug=True)
