defmodule Emitter do

  defmacro emit(do: block) do
    quote do
      {:ok, var!(buffer, Emitter)} = Buffer.start([])

      unquote(Macro.postwalk(block, &code_generation/1))
      
      result = Buffer.render(var!(buffer, Emitter))
      Buffer.stop(var!(buffer, Emitter))

      result
    end 
  end

  defp push(code) do
    quote do
      Buffer.put var!(buffer, Emitter), "#{unquote(code)}"
    end
  end

  defp code_generation({:__block__, _meta, args}) do
    Enum.map(args, fn function ->
      IO.inspect function
      push(function) end) 
  end

  defp code_generation(ast) do
    ast
  end
end

