$(document).ready(function () {
    $('#submit').on('click', function () {
        // ajax call goes here, but get text from text input first, should do validation here, its easier
        var command = $('#command-input').text();
        if (command == '') {
          // error, no input
        } else {
          $.ajax({
              method: 'GET',
              url: 'http://localhost:63342/game/input',
              data: { input: command }
          }).done(function (object) {
              $('#room-info').append(object.currentRoom);
              $('#inventory-info').append(object.currentInventory);
          });
        }
    });
});
