defmodule CodeWriter do
  @moduledoc """
  This module corresponds to the code translater module.
  """

  @doc """
  It takes the parsed stream and translates it into

  Translating and writing will happen in the following steps
  1. initial translation
  2. Identifying symbols
  3. Replacing symbols
  4. writing to the file

  """
  require Emitter
  import Emitter
  import AsmDsl

  def write(commands, path) do

    content =
    commands
    |> Enum.map(fn line -> translate(line) end)
    |> find_symbols
    |> replace_symbols

    to_file(content, path) 
  end

  defp find_symbols(input) do
    input
  end

  defp replace_symbols(input) do
    input
  end

  defp to_file(content, path) do
    {:ok, f} = File.open path, [:write]

    if !is_nil(f) do
      IO.write(f, content)
      File.close f

      {:ok}
    else 
      {:error, "File not found"}
    end  
  end

  defp translate({:c_arithmetic, :add}) do
    emit do
      bin_func_setup()
      a "R13"
      c "D=D+M"
      push()
    end 
  end

  defp translate({:c_arithmetic, eq}) do
    emit do
      bin_func_setup()
      a "R13"
      c "D=D-M"
      a "True"
      c "D;JEQ", "//If then setup"
      load "0"
      push()
      a "End"
      c "0;JMP"
      c "(True)"
      load "1"
      push()
      c "(End)"
    end
  end

  defp translate({:c_push, :constant, number}) do
    IO.puts "into push constant"
    emit do
      load number 
      push()
      sp_inc()
    end
  end

  defp translate({:c_push, segment, index}) do
    "push\n"
  end

  defp translate({:c_pop, segment, index})do
    "pop\n"
  end

  defp translate(unknown) do
    "Unknown command #{unknown}"
  end

end

