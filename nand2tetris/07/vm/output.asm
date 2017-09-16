@7          // loading value 7
D=A
@SP          // write D to M[SP]
A=M
M=D
@SP          // increment SP
D=M
M=D+1
@8          // loading value 8
D=A
@SP          // write D to M[SP]
A=M
M=D
@SP          // increment SP
D=M
M=D+1
@SP          // decrement SP
D=M
M=D-1
@SP          // load M[SP] value onto register D
A=M
D=M
@R13          //Save in register R13 value of register D
M=D
@SP          // decrement SP
D=M
M=D-1
@SP          // load M[SP] value onto register D
A=M
D=M
@R13
D=D+M
@SP          // write D to M[SP]
A=M
M=D
