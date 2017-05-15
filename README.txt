Matrix calculator.

    You can input matrices like (without ;) :
    > [[12]];
    > [[4,7.12],[-8.13, 6]];
    > [[-3.820,8,1,12], [5, 7, 9, 300]];

    This calculator maintains adding, subtraction, multiplication, rang, transposition.
    Also you can assign matrices and use them later.

    Example 1 :

    > A = [[45,19], [8,2]]
    45.0 19.0
    8.0 2.0

    > B = [[-3, 8.23],[7, 166]]
    -3.0 8.23
    7.0 166.0

    > C = A
    45.0 19.0
    8.0 2.0

    >(A - B ^T) * rank(C)
    96.0 24.0
    -0.46 -328.0

    Example 2 :

    > A = [[12]]
    12.0

    > B = [[7,18],[9,812.3]]
    7.0 18.0
    9.0 812.3

    > C = rank(B)
    You can assign just matrices!

    > C = [[5]]
    5.0

    > A + C
    17.0

    > rank(B) * (A+C)
    34.0

    > rank(B)
    2

    > (A+C)^T
    17.0

    > B ^T
    7.0 9.0
    18.0 812.3


For creation were used ANTLR4 and JAMA.