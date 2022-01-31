# BasicLibrary
BasicLibrary is a toy Java Spring Boot application that attempts to implement some basic calls to post, put, get, and delete objects within a library. 

Based on lessions from Codecademy

## Possible Calls: 
### Welcome:
- Get: /library/  
  Sends welcome message. 

### Functionality:
- Post: /library/addBook?title=<title>&contents=<contents>  
  Adds book to the library  
  Title: Title of the book  
  Contents: Contents of the book  

- Put: /library/updateBookContents?title=<title>&newContents=<contents>  
  Updates contents of a book  
  Title: Title of the book   
  NewContents: New book contents  
  Errors Possible: NOT_FOUND (title not found)  
  
- Get: /library/getBook?title=<title>  
  Gets the cotnents of a book in the library  
  Title: Title of the book  
  Errors Possible: NOT_FOUND (title not found)  

- Get: /library/getAllBooks  
  Returns list of all book titles in the library (sorted alphabetically)  
  Errors Possible: NOT_FOUND (no books found)  
  
- Delete: /library/removeBook?title=<title>  
  Removes book from the library  
  Title: Title of the book  
  Errors Possible: NOT_FOUND (title not found)  
