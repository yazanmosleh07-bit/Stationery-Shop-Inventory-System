# Stationery Shop Management System

A console-based Java application that simulates the daily operations of a stationery shop: selling products, restocking from suppliers, and tracking inventory.

Built for the **Object-Oriented Programming** course project (OOP_Course_0130231), Al-Zaytoonah University of Jordan, 2026-2027 first semester.

## Features

- Sell a product (customer sale)
- Buy stock from a supplier (restock)
- Check current stock
- Add a new product to the inventory
- Input validation for invalid and negative values

## OOP Concepts Used

| Concept | Where it is used |
|---|---|
| **Objects and Classes** | `Product`, `InventoryClass`, `Main` |
| **Inheritance** | `Pen` extends `Product`; `Manager` and `Cashier` extend `Employee` |
| **Polymorphism** | Subclasses override `calculatePrice()` / `getInfo()`, called through a `Product` reference |
| **Composition** | `Sale` creates and owns its `SaleItem` list |
| **Aggregation** | `Shop` holds an `InventoryClass`, employees and suppliers |
| **Encapsulation** | Private fields with getters and setters |

## Project Structure

```
src/
├── Main.java              # Menu loop and user input
├── Shop.java              # Aggregates inventory, employees, suppliers
├── InventoryClass.java    # ArrayList wrapper for products
├── Product.java           # Base product class
├── Pen.java               # Product subclass
├── Sale.java              # Owns SaleItems, prints receipt
├── SaleItem.java
├── Employee.java          # Base employee class
├── Manager.java
├── Cashier.java
└── Supplier.java
```

> Some of these files are still planned. See the progress checklist below.

## How to Run

Requires JDK 8 or later.

```bash
cd src
javac *.java
java Main
```

## Menu

```
1. Sell a product      (customer sale)
2. Buy stock           (restock from supplier)
3. Check stock
4. Add to stock
0. Exit
```

## Progress Checklist

### Done

- [x] `Product` class with constructor, getters, `addStock`
- [x] `InventoryClass` wrapping an `ArrayList<Product>`
- [x] `Pen` subclass created (needs fixing)
- [x] Console menu loop in `Main`
- [x] Safe input helpers (`readInt`, `readDouble`)
- [x] Sell a product
- [x] Check stock
- [x] Add to stock

### Fix first (bugs)

- [ ] `calculatePrice` uses `quantity` (stock) instead of `qty`
- [ ] `removeStock` can go negative, so check `amount <= quantity`
- [ ] `id` is never assigned, so add a static counter
- [ ] `printStock()` prints object references, so add `toString()`
- [ ] Merge duplicate `checkStock()` / `showProductList()`
- [ ] Rename `penClass` to `Pen` and fix the `qunatity` typo
- [ ] Make `Product` fields `private`

### OOP requirements

**Inheritance**

- [ ] `Pen` is actually created and used from `Main`
- [ ] Add `Notebook`, `Paper` (or similar) subclasses
- [ ] Add `Employee` superclass with `Manager` and `Cashier`

**Polymorphism**

- [ ] `Pen.calculatePrice(int qty)` uses `@Override` with the same signature
- [ ] Other subclasses override `calculatePrice()` or `getInfo()`
- [ ] Call it through a `Product` reference (e.g., `Product p = new Pen(...)`)
- [ ] Optional: `Payment` with `CashPayment` / `CardPayment`

**Composition**

- [ ] `Sale` class that creates and owns its `SaleItem` list
- [ ] `SaleItem` class (product + quantity)
- [ ] `printReceipt()` on `Sale`

**Aggregation**

- [ ] `Shop` class holding `InventoryClass`, employees and suppliers
- [ ] `Main` talks to `Shop` instead of directly to the inventory

### Features

- [ ] Option 2: Buy stock (with a `Supplier` class)
- [ ] Sell multiple products in one sale, then show the receipt
- [ ] Remove or update a product
- [ ] Choose product type when adding stock

### Error handling and polish

- [ ] Handle empty names and zero or negative prices
- [ ] Handle selling or removing more than available
- [ ] Clear comments on every class and method
- [ ] Split classes into separate files with sensible names
- [ ] Test every menu option once, including bad input

### UML

- [ ] Finalize the classes first
- [ ] Class diagram with fields and methods
- [ ] Inheritance arrows (hollow triangle)
- [ ] Aggregation (hollow diamond) and composition (filled diamond)
- [ ] Check it matches the final code
- [ ] Neat labels and layout

### Discussion prep

- [ ] Be ready to demo: sell, buy, receipt
- [ ] Be ready to explain why each OOP concept is used where it is
- [ ] Prepare 2-3 things you'd improve (reflection)

**Suggested order:** bugs → `Pen` and polymorphism → `Sale` composition → `Shop` aggregation → employees → buy stock → polish → UML → discussion prep.

## Author

Yazan, Computer Science, Al-Zaytoonah University of Jordan
