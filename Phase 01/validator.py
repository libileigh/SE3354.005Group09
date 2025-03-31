def validate_input(user_input):
    if len(user_input) != 3:
        return False

    try:
        float(user_input[0]) 
        float(user_input[2])  
    except ValueError:
        return False  

    if user_input[1] not in ['+', '-', '*', '/']:
        return False

    return True