// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random fact to the page.
 */
function addRandomFact() {
  const facts =
    ['I am a virgo ♍ ', 'I have been on Television before 📺 ' , 'I love the 90s show Golden Girls 👵👵👵 ']; 

  // Pick a random fact.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
}

/**

 * Fetch the list of comments and add them to the page
 */
function fetchAndAddComments(){
    fetch('/comments').then(response => response.json()).then((comment) => {
        const commentContainer = document.getElementById('comments-container');
        comment.forEach(function(comment) {
            commentContainer.appendChild(createCommentElement(comment))
        });
    });
}

/** Creates and returns a comment element containing  the components
 of a comment. */
function createCommentElement(comment) {  
  const commentUsername = document.createElement('username');  
  commentUsername.innerText = comment.username;

  const commentDate = document.createElement('date');
  commentDate.innerText = comment.date;

  const commentText = document.createElement('comment');
  commentText.innerText = comment;

  const commentElement = document.createElement('element');
  commentElement.appendChild(commentUsername);
  commentElement.appendChild(commentDate);
  commentElement.appendChild(comment);
  return commentElement;
}
