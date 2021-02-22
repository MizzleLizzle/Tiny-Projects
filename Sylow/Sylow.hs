isPrime :: Integral t => t -> Bool
isPrime k = if k > 1 then null [ x | x <- [2..k -1], k `mod` x == 0] else False

biggestPrimeFactorHelper :: Integral t => t -> t -> t
biggestPrimeFactorHelper n k
    | isPrime n = n
    | n `mod` k == 0 && isPrime k = k
    | otherwise = biggestPrimeFactorHelper n (k-1)

biggestPrimeFactor :: Integral t => t -> t
biggestPrimeFactor n = biggestPrimeFactorHelper n n

primeFactors :: Integral t => t -> [t]
primeFactors 1 = []
primeFactors n = (biggestPrimeFactor n) : (primeFactors (div n (biggestPrimeFactor n)))

deleteFrom :: Eq t => t -> [t] -> [t]
deleteFrom _ [] = []
deleteFrom el (x:xs)
    | el == x = deleteFrom el xs
    | otherwise = x : deleteFrom el xs

count :: (Num p, Eq t) => t -> [t] -> p
count el [] = 0
count el (x:xs)
    | el == x = 1 + count el xs
    | otherwise = count el xs

elementsWithMultiplicity :: (Num p, Eq t) => [t] -> [(t, p)]
elementsWithMultiplicity [] = []
elementsWithMultiplicity (x:xs) = (x, count x (x:xs)) : (elementsWithMultiplicity (deleteFrom x xs))

divisors :: Integral t => t -> [t]
divisors n = [x | x <- [1..n], n `mod` x == 0]

sylowMultiplicitys :: Integral t => (t, p) -> t -> [t]
sylowMultiplicitys (p,n) m = filter (\x -> x `mod` p == 1) (divisors m)

listSylowMultiplicitys :: Integral t => [(t, t)] -> t -> [(t, t, [t])]
listSylowMultiplicitys [] groupOrder = []
listSylowMultiplicitys ((p,n):xs) groupOrder = (p,n,sylowMultiplicitys (p,n) (groupOrder `div` (p^n))) :  (listSylowMultiplicitys xs groupOrder)

sylow :: Integral t => t -> [(t, t, [t])]
sylow groupOrder = listSylowMultiplicitys (elementsWithMultiplicity (primeFactors groupOrder)) groupOrder

showSetHelper :: Show a => [a] -> [Char]
showSetHelper [] = []
showSetHelper [x] = show x
showSetHelper (x:xs) = show x ++ ", " ++ showSetHelper xs

showSet :: Show a => [a] -> [Char]
showSet xs = "{" ++ showSetHelper xs ++ "}"

showSylow :: (Integral t, Show t) => (t, t, [t]) -> [Char]
showSylow (p, n, [1]) = "There is a Sylow " ++ (show p) ++ "-Subgroup of Order " ++ show (p^n) ++ ". It is a normal subgroup."
showSylow (p, n, xs) = "There are Sylow " ++ (show p) ++ "-Subgroup of Order " ++ show (p^n) ++ ", the number of which is in " ++ showSet xs ++ "."


main :: IO ()
main = do
    putStrLn "Enter group order:"
    groupOrderStr <- getLine
    let
        groupOrder = read groupOrderStr :: Int in
            putStrLn (unlines (map showSylow (sylow groupOrder)))
        
    