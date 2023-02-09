var main = {
    init: function () {
        let _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#send').on('click', function () {
            _this.interactive();
            _this.openAi();
        })

    },
    save: function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (id) {
            alert(JSON.stringify(id) + '번 글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            $('#warning-text').text("글을 작성할 수 있는 권한이 없습니다.");
        });
    },
    update: function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val(),
        };

        let id = $('#id').val()

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (id) {
            alert(JSON.stringify(id) + '번 글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete: function () {

        let id = $('#id').val()

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function (id) {
            alert(JSON.stringify(id) + '번 글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    openAi: function () {
        let text = $('#input').val();
        let data = {
            'model': "text-davinci-003",
            'prompt': text,
            'temperature': 0.7,
            'max_tokens': 256,
            'top_p': 1,
            'frequency_penalty': 0,
            'presence_penalty': 0
        };
        $.ajax({
            type: 'POST',
            url: 'https://api.openai.com/v1/completions',
            headers: {
                Authorization: 'Bearer ' + $('#apikey').val(),
            },
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (res) {
            let text = res.choices[0].text;
            let template = `    <div class="line">
        <span class="chat-box">${text}</span>
    </div>`
            $('.chat-content').append(template).scrollTop($('.chat-content')[0].scrollHeight);
        }).fail(function (error) {
            $('#warning-text').text("글을 작성할 수 있는 권한이 없습니다.");
        });
    },

    interactive: function () {
        let text = $('#input').val();
        let template = `    <div class="line">
        <span class="chat-box mine">${text}</span>
    </div>`
        $('.chat-content').append(template).scrollTop($('.chat-content')[0].scrollHeight);
    },
}

main.init();