defmodule Parser do
  def parse(stream) do
    analyze stream
  end

  defp analyze(stream) do
      stream 
      |> Enum.map(&String.strip/1)
      |> Enum.map(fn line -> process line end)
      |> Enum.filter(fn item -> !is_nil(item) end) 
  end

  # skip empty lines
  defp process("") do
  end

  # skip comment lines
  defp process("//" <> _comment) do
  end

  # process push commands
  defp process("push" <> arguments) do
    [arg1, arg2 | _rest] = 
    arguments
    |> String.strip
    |> String.split(" ")

    {:c_push, String.to_atom(arg1), arg2}
  end

  # process push commands
  defp process("pop" <> arguments) do
    [arg1, arg2 | _rest] = 
    arguments
    |> String.strip
    |> String.split(" ")

    {:c_pop, String.to_atom(arg1), arg2}
  end

  #process arithmetic
  defp  process("add" <> _rest) do
    {:c_arithmetic, :add}
  end

  #eq
  defp process("eq" <> _rest) do
    {:c_arithmetic, :eq}
  end

  # default
  defp process(line) do
    IO.puts line
  end

end

