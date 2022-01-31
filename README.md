# BasicLibrary
BasicLibrary is a toy Java Spring Boot application that attempts to implement some basic calls to get, post, delete objects within a library. 

Based on lessions from Codecademy

Possible Calls: 

- /library/addBook?title=<title>&contents=<contents>
  Adds book to the library
  Title: Title of the book
  Contents: Cntents of the book
  
- /library/removeBook?title=<title>
  Removes book from the library
  Title: Title of the book
  
- /library/getBook?title=<title>
  Gets the cotnents of a book in the library
  Title: Title of the book

- /library/getAllBooks
  Returns all books in the library
