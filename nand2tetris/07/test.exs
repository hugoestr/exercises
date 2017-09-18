defmodule Test do

  def test() do
    """
    one line
    a second line
    a third line
    """
  end


  def permutation(list) do
   list
   |> perm
   |> Enum.map(fn item -> 
               IO.inspect(item) 
               IO.puts("limit") end)
  end

  def perm([a]) do
    [a]
  end


  def perm(list) do
     list
     |> Enum.map(fn head -> 
               List.delete(list, head) 
               |> perm
               |> Enum.map(fn tail -> 
                           [head |[tail]] end) end)
  end



  def flatten([], acc) do
    acc 
  end

  def flatten([head|tail], acc) do
    state = acc ++ head
    flatten(tail, state)
  end
    
  # go down the list
  # when the list doesn't contain other lists return that
  #
  # or a flatten with levels. Flatten so far, return list 
  #[[:a, :b, :c], [:a, :c, :b], [b:, :a, :c],... [last permutation]]
  #


end

