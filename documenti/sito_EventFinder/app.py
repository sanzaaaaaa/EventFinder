from flask import Flask, render_template

app = Flask(__name__)

@app.route("/")
def home():
    eventi = [
        {"id": 1, "nome": "Concerto Rock", "data": "10 Aprile 2025", "luogo": "Milano", "immagine": "concerto.jpg"},
        {"id": 2, "nome": "Festival del Cibo", "data": "15 Maggio 2025", "luogo": "Roma", "immagine": "food_festival.jpg"},
        {"id": 3, "nome": "Maratona Cittadina", "data": "20 Giugno 2025", "luogo": "Napoli", "immagine": "maratona.jpg"},
    ]
    return render_template("index.html", eventi=eventi)

@app.route('/')
def home():
    return render_template('index.html')

@app.route('/amici')
def amici():
    return render_template('amici.html')

@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/register')
def register():
    return render_template('register.html')

@app.route('/eventi')
def eventi():
    return render_template('eventi.html')

@app.route('/contatti')
def contatti():
    return render_template('contatti.html')

@app.route('/account')
def account():
    return render_template('account.html')

if __name__ == '__main__':
    app.run(debug=True)
