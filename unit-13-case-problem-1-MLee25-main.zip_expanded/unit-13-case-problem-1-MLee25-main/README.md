[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-c66648af7eb3fe8bc4f294546bfd86ef473780cde1dea487d3c4ff354943c9ae.svg)](https://classroom.github.com/online_ide?assignment_repo_id=7704363&assignment_repo_type=AssignmentRepo)
# Wilmu-2022-Unit-13-Case-Problem-1

In this lab, you will be creating a Playlist class that involves file input and output.

## Files for this case problem:
For this case problem, you will need the following files:
- Song.java
- Playlist.java
- PlaylistDemo.java

## Song.java Requirements
This file has been provided for you.  Do not make any changes to it.

## Playlist.java Requirements
This file has been provided for you.  Do not make any changes to it.

## PlaylistDemo.java Requirements
A starter file has been provided for you.  Do not change any of the existing code.  You need to add new methods and add on to the existing main method.

- Define a savePlaylist method
  - Include a parameter for the playlist to save
  - This method will attempt to save the details of the list of songs to a file.
  - It will return true if it successfully saves the file.  It will return false if it doesn't save the file.
  - The file path will need to use the playlist name as the name of the file with a file extension of .txt
    - Example: dance.txt
    - Do not use an absolute file path that includes the complete file path as this is not guaranteed to be the same on all systems
  - Use the proper options so that it will create a new file if one doesn't exist and overwrite existing content if the file does exist
  - When writing data to the file
    - Information for each song needs to be on its own line
      - This should include the title and artist
      - Use a delimiter that is unlikely to be found as a part of a song title or artist
      - Since commas are likely to be found, it may be better to use something like "\t" as the delimiter
    - Do not write the result of toString to the file as this includes additional repeated keywords that are unnecessary

- Define an openPlaylist method
  - Include a parameter for the name of the playlist to open
  - This method will attempt to open a saved file with the specified name
    - The parameter is just the name of the playlist, so you will need to add ".txt" to the end for the file path
  - It will return a Playlist if it successfully opens and reads the information.
  - It will return null if it is unsuccessfully or there is invalid data
  - The name to use for the playlist will need to be the specified name
  - The songs will need to be created and added to a list of songs as you read in each line
  - When all data is read in, the name and list of songs will be used to construct a playlist that is returned as long as everything is successful
  - If anything is unsuccessful in the process, be sure to provide an error message to the user to let them know the problem before returning null

- Update the main method
  - Currently it will create a playlist based on user input and print out the details
  - After printing out the details, print out "Attempting to save the playlist" and call the appropriate method to attempt to save the playlist
  - If the playlist was saved successfully, print out "Playlist successfully saved"
  - Then, print out "Attempting to open the playlist" and call the appropriate method to attempt to open the playlist
  - If the playlist is opened successfully, print out "Playlist successfully opened" and then print out the details of the playlist again