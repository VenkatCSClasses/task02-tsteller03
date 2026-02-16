BankAccount class: Initialize variables String email, the email associated with the account, and double balance, the account's current balance.

Constructor: Initializes the BankAccount instance by checking if isEmailValid and isAmountValid functions return true, and throws an IllegalArgumentException if either or both functions return false.

getBalance: Returns the BankAccount balance.

getEmail: Returns the BankAccount email.

deposit: Takes the parameter amount - increases the BankAccount balance by the amount specified if amount is non-negative and smaller than balance. Throw an IllegalArgumentException if amount is negative or has an unacceptable amount of decimal places.

withdraw: Takes the parameter amount - reduces the BankAccount balance by the amount specified if amount is non-negative and smaller than balance. Throw an IllegalArgumentException if amount is negative or has an unacceptable amount of decimal places. Throw an InsufficientFundsException if amount is larger than current balance.

transfer: Takes the parameters account and amount - reduces the original BankAccount balance and increase the passed BankAccount's balance by the amount specified. Throw an IllegalArgumentException if amount is negative or has an unacceptable amount of decimal places. Throw an InsufficientFundsException if amount is larger than current balance.

isAmountValid: Takes the parameter amount - returns true if amount is positive and at most two decimal places, false if otherwise.

isEmailValid: Takes the parameter email - returns true if email abides by standard email naming rules, false if otherwise. Email cannot be blank or null, must contain only one @ symbol, contain no special characters aside from ., -, or _, said acceptable special characters cannot appear twice in a row or at the start and end of the email's prefix or domain.