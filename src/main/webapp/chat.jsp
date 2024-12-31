<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chat Application</title>
    <script>
        var socket;

        // Connect to the WebSocket server
        function connect() {
            socket = new WebSocket("ws://localhost:8080/chat");
            socket.onmessage = function(event) {
                var chatBox = document.getElementById("chatBox");
                chatBox.innerHTML += "<br/>" + event.data; // Display incoming message
                chatBox.scrollTop = chatBox.scrollHeight; // Scroll to the bottom
            };
        }

        // Send message via WebSocket
        function sendMessage() {
            var messageInput = document.getElementById("messageInput");
            var message = messageInput.value;
            if (message) {
				var chatBox = document.getElementById("chatBox");
				                chatBox.innerHTML += "<br/>" + "You: "+message; // Display incoming message
				                chatBox.scrollTop = chatBox.scrollHeight;
                messageInput.value = ""; // Clear the input field
                socket.send(message); // Send the message to WebSocket
            }
        }

        window.onload = connect; // Initialize WebSocket connection when page loads
    </script>
</head>
<body>
    <h1>Chat Application</h1>
    <div id="chatBox" style="border: 1px solid #000; height: 300px; overflow-y: scroll;"></div>
    <input type="text" id="messageInput" placeholder="Type a message" style="width: 80%;"/>
    <button onclick="sendMessage()">Send</button>
</body>
</html>
