# Discount Rules Engine using Scala

## Overview
The Discount Rule Engine is designed to automate the process of qualifying orders for discounts and calculating the appropriate discount based on a set of predefined rules. This engine is implemented in Scala, leveraging functional programming paradigms to ensure immutability, pure functions, and higher-order functions. The engine reads order data from a CSV file, applies discount rules, calculates final prices, logs events for monitoring, and inserts the processed data into an Oracle database.

## Features
- **Qualify Orders for Discounts**: Determine eligibility for discounts based on various rules.
- **Calculate Discounts**: Apply specific discount calculations based on conditions such as product type, expiration date, quantity sold, special dates, and payment methods.
- **Logging**: Log engine interactions and errors for debugging and auditing purposes.
- **Database Interaction**: Store processed order data in an Oracle database.

## Discount Rules
### Quantity Qualifier Rule
- **Condition**: Quantity of a product in an order exceeds 5 units.
- **Discount**:
  - 5% for 6-9 units
  - 7% for 10-14 units
  - 10% for more than 15 units

### Productt Type Qualifier Rule
- **Condition**: Order contains wine or cheese products.
- **Discount**:
  - 5% for wine products
  - 10% for cheese products

### Expiry Day Qualifier Rule
- **Condition**: Product has less than 30 days remaining before expiration.
- **Discount**: Gradual discount from 1% to 29%, based on the number of days remaining.

### Special Day Qualifier Rule
- **Condition**: Orders made on the 23rd of March.
- **Discount**: 50% discount for orders placed on this date.

### Channel Qualifier Rule
- **Condition**: Sale made through the App.
- **Discount**:
  - 5% for 1-5 units
  - 10% for 6-10 units
  - 15% for more than 10 units

### Payment Methods Qualifier Rule
- **Condition**: Order paid using a Visa card.
- **Discount**: 5% discount for orders paid with Visa cards.

## Technical Requirements
- **Functional Programming**: Emphasis on immutability and pure functions.
  - No mutable variables or data structures.
  - No loops allowed.
  - Functions must be pure, with predictable behavior and no side effects.
- **Logging**: Detailed logging for monitoring and debugging.
- **Database**: Oracle DBMS for storing processed information.
- **CSV Export**: Output processed orders to a CSV file.

## Implementation Details
### Language and Paradigms
- **Scala**: The engine is implemented in Scala, taking advantage of its support for functional programming, imperative programming, and OOP.

### Processing Flow
1. **Read Order Data**: Parse orders from a CSV file.
2. **Apply Discount Rules**: Check each order against the discount rules and calculate applicable discounts.
3. **Handle Multiple Discounts**: If an order qualifies for multiple discounts, apply the average of the top 2 discounts.
4. **Log Events**: Log each transaction and any relevant information or warnings.
5. **Store Data**: Insert processed orders into an Oracle database.

## Conclusion
The Discount Rule Engine is a powerful tool for retail stores to automate the process of qualifying orders for discounts and calculating the appropriate discount. Its functional programming approach ensures robust, maintainable, and predictable code. The engine not only simplifies the discount application process but also provides flexibility for adding new rules and criteria in the future.
