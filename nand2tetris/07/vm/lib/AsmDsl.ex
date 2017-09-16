defmodule AsmDsl do
 require Emitter
 import Emitter


 def a(address, comment \\ "") do
  c("@#{address}", comment)
 end

 def c(command, comment \\ "") do
   cond do
    String.trim(comment) == "" -> 
      "#{command}\n" 
   true ->
      "#{command}          #{comment}\n"
   end
 end

  def load(address) do
    emit do
      a address, "// loading value #{address}"
      c "D=A"
    end
  end

  def stow(register \\ "R13") do
    emit do
      pop
      a register, "//Save in register #{register} value of register D"
      c "M=D"
    end
  end

  def bin_func_setup() do
    emit do
      sp_dec
      stow
      sp_dec
      pop
    end
  end

  def pop() do
    """ 
    @SP          // load M[SP] value onto register D
    A=M
    D=M
    """
  end

  def push() do
    """
    @SP          // write D to M[SP]
    A=M
    M=D
    """
  end

   def sp_inc() do
    """
    @SP          // increment SP
    D=M
    M=D+1
    """
  end

  def sp_dec() do
   """
   @SP          // decrement SP
   D=M
   M=D-1
   """
  end
end
