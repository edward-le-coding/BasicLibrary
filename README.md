# BasicLibrary
BasicLibrary is a toy Java Spring Boot application that attempts to implement some basic calls to get, post, delete objects within a library. 

Based on lessions from Codecademy

Possible Calls: 

- Post: /library/addBook?title=<title>&contents=<contents>
  Adds book to the library
  Title: Title of the book
  Contents: Contents of the book

- Put: /library/updateBookContents?title=<title>&newContents=<contents>
  Updates contents of a book
  Title: Title of the book 
  NewContents: New book contents
  
- Get: /library/getBook?title=<title>
  Gets the cotnents of a book in the library
  Title: Title of the book

- Get: /library/getAllBooks
  Returns all books in the library
  
- Delete: /library/removeBook?title=<title>
  Removes book from the library
  Title: Title of the book
