from input_handler import get_inputfrom operations import add, subtract, multiply, dividefrom display import display_resultfrom validator import validate_input
def main():
    while True:
        user_input = get_input()
        if validate_input(user_input):
            num1, op, num2 = user_input
            if op == '+':
                result = add(num1, num2)
            elif op == '-':
                result = subtract(num1, num2)
            elif op == '*':
                result = multiply(num1, num2)
            elif op == '/':
                result = divide(num1, num2)
            display_result(result)
        else:
            print("Invalid input")
if __name__ == "__main__":
    main()
