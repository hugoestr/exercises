defmodule VM do
  @moduledoc """
    Entry point to the VM 
  """

  @doc """
  The entry to the parser. Right now it only outputs to output.asm.
  It may change later.

  Parser and codewriter correspond to the two modules described in the 
  book.  
  """
  def main(args) do

      [file|_rest]  =
      args
      |> parse_args

      status = 
        file
        |> open_stream
        |> Parser.parse
        |> CodeWriter.write("output.asm") 

      case status do
        {:ok }            -> IO.puts "" 
        {:error, message} -> IO.puts message
        _                 -> IO.puts "Unknown error"
      end
  end

  defp parse_args(args) do
    {_, arguments, _} =  
    args
    |> OptionParser.parse(switches: [])

    arguments
  end

  defp open_stream(path) do
    try do
       File.stream!(path, [:utf8])
    rescue
      _  -> {:error, "File not found"} 
    end
  end
end
