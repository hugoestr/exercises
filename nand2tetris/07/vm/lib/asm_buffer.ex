defmodule Buffer do
   def start(state) do 
    Agent.start_link(fn -> state end)
  end 

  def stop(buffer), do: Agent.stop(buffer)

  def put(buffer, content), do: Agent.update(buffer, &[content | &1])

  def render(buffer) do 
    Agent.get(buffer, &(&1))
    |> Enum.reverse
    |> Enum.join
  end
end
