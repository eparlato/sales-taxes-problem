# TODO

Refactor RegexPurchaseBuilder.calculateTax(), extract a decorator?

taxedProducts in RegexPurchaseBuilder is a catalog of products, extract a Catalog interface? 

CashRegister.process() creates a Receipt, should I pass it as a collaborator instead?