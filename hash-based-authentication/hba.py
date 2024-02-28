import hashlib

def hash_password(password):
    # using sha256 hashing algorithm
    sha_signature = hashlib.sha256(password.encode()).hexdigest()
    return sha_signature

def store_hashed_password(hashed_password, username):
    # to store this securely in a real implementation such as in a database with restricted access
    with open(f"{username}_password_hash.txt", "w") as file:
        file.write(hashed_password)

def authenticate_user(username, password):
    # to read user hashed password
    try:
        with open(f"{username}_password_hash.txt", "r") as file:
            stored_hashed_password = file.read()
            # has password provided and compare
            if hash_password(password) == stored_hashed_password:
                return True
            else:
                return False
    except FileNotFoundError:
        print("user does not exist.")
        return False

# example
username = "sarthak"
new_password = "sarthakhandelwal"
hashed_pw = hash_password(new_password)
store_hashed_password(hashed_pw, username)

attempted_password = input("enter your password to login: ")
auth_result = authenticate_user(username, attempted_password)
if auth_result:
    print("authentication successful!")
else:
    print("authentication failed!")