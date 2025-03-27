

from flask import Flask, request, jsonify, render_template
import pymysql
import pymysql.cursors

connection = pymysql.connect(
    host='localhost',
    user='root',
    password='1234',
    database='eventfinder',
    cursorclass=pymysql.cursors.DictCursor,
    autocommit=True,
)

app = Flask(__name__)

@app.route('/')
def home():
    query = "SELECT * FROM Utenti"
    with connection.cursor() as cursor:
        cursor.execute(query)
        lista = cursor.fetchall()
    
   
    for utente in lista:
        if utente['data_di_nascita']:
            utente['data_di_nascita'] = utente['data_di_nascita'].strftime('%d/%m/%y')
    
    return jsonify(lista)



@app.route('/api/register', methods=['POST'])
def register():
    data = request.get_json()
  
    nome = data.get('nome')
    cognome = data.get('cognome')
    email = data.get('email')
    data_di_nascita = data.get('data_di_nascita')
    password = data.get('password')

   
    cursor = connection.cursor()
    query = "INSERT INTO utenti (nome, cognome, email, data_di_nascita, password) VALUES (%s, %s, %s, %s, %s)"
    cursor.execute(query, (nome, cognome, email, data_di_nascita, password))
    connection.commit()
    cursor.close()
    return jsonify({"message": "Utente registrato correttamente!"}), 201

@app.route('/api/login', methods=['POST'])
def login():
    data = request.get_json()

  
    email = data.get('email')
    password_plain = data.get('password')

    
    query = "SELECT * FROM utenti WHERE email = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (email,))
        user = cursor.fetchone()

        if user:
           
            stored_password = user['password']
            if password_plain == stored_password:
                return jsonify({'message': 'Login effettuato con successo!'}), 200
            else:
                return jsonify({'message': 'Password errata'}), 401
        else:
            return jsonify({'message': 'Email non trovata'}), 404


''''
@app.route("/")
def home():
    eventi = [
        {"id": 1, "nome": "Concerto Rock", "data": "10 Aprile 2025", "luogo": "Milano", "immagine": "concerto.jpg"},
        {"id": 2, "nome": "Festival del Cibo", "data": "15 Maggio 2025", "luogo": "Roma", "immagine": "food_festival.jpg"},
        {"id": 3, "nome": "Maratona Cittadina", "data": "20 Giugno 2025", "luogo": "Napoli", "immagine": "maratona.jpg"},
    ]
    return render_template("index.html", eventi=eventi)


@app.route('/amici')
def amici():
    return render_template('amici.html')
'''


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
