﻿import unittestfrom operations import add, subtract, multiply, divide
class TestCalculator(unittest.TestCase):


    def test_add(self):
        self.assertEqual(add(2, 3), 5)


    def test_subtract(self):
        self.assertEqual(subtract(5, 3), 2)


    def test_multiply(self):
        self.assertEqual(multiply(2, 3), 6)


    def test_divide(self):
        self.assertEqual(divide(6, 3), 2)
        self.assertEqual(divide(6, 0), "Error: Division by zero")
if __name__ == '__main__':
    unittest.main()