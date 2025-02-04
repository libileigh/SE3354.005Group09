from input_handler import get_input
from operations import add, subtract, multiply, divide
from display import display_result
from validator import validate_input

def main():
    while True:
        user_input = get_input()
        if validate_input(user_input):
            num1, op, num2 = user_input
            if op == '+':
                result = add(float(num1), float(num2))
            elif op == '-':
                result = subtract(float(num1), float(num2))
            elif op == '*':
                result = multiply(float(num1), float(num2))
            elif op == '/':
                result = divide(float(num1), float(num2))
            display_result(result)
        else:
            print("Invalid input")

if __name__ == "__main__":
    main()
