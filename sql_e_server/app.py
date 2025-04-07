from flask import Flask, request, jsonify
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
    query = "SELECT * FROM utenti"  
    with connection.cursor() as cursor:
        cursor.execute(query)
        lista = cursor.fetchall()
    
   
    for utente in lista:
        if utente['data_di_nascita']:
            utente['data_di_nascita'] = utente['data_di_nascita'].strftime('%Y/%m/%d')
    
    return jsonify(lista)

@app.route('/register', methods=['POST'])
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


@app.route('/login', methods=['POST'])
def login():
    data = request.get_json()

    email = data.get('email')
    password_plain = data.get('password') 

   
    query =  "SELECT nome, cognome, email, data_di_nascita, password FROM utenti WHERE email = %s"
    with connection.cursor() as cursor:
        cursor.execute(query, (email,))
        user = cursor.fetchone()

        if user:
       
            if password_plain == user['password']:
           
                data_nascita = user['data_di_nascita'].strftime('%d-%m-%Y') 

               
                return jsonify({
                    'nome': user['nome'],
                    'cognome': user['cognome'],
                    'email': user['email'],
                    'data_di_nascita': data_nascita
                }), 200
            else:
                return jsonify({'message': 'Password errata'}), 401
        else:
            return jsonify({'message': 'Email non trovata'}), 404



@app.route('/get_users', methods=['GET'])
def get_users():
    query = "SELECT nome, cognome FROM utenti" 
    with connection.cursor() as cursor:
        cursor.execute(query)
        users = cursor.fetchall() 

    return jsonify(users) 




if __name__ == '__main__':
    app.run(host="0.0.0.0", debug=True)