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

        $('#btn-send').on('click', function () {
            _this.question();
        });

        $('#prompt').on('keypress', function (event) {
            if (event.key === 'Enter') {
                _this.question();
            }
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

    answer: function (prompt) {
        $.ajax({
            type: 'POST',
            url: '/api/v1/openai/send',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: prompt
        }).done(function (res) {
            let text = res.choices[0].text;
            let template = `    <div class="line">
        <span class="chat-box">${text}</span>
    </div>`
            $('.chat-content').append(template).scrollTop($('.chat-content')[0].scrollHeight);
        }).fail(function (error) {
            $('#warning-text').text("유효하지 않은 요청입니다.");
        });
    },

    question: function () {
        let text = $('#prompt').val();
        if (text === "") {
            return;
        }
        let template = `    <div class="line">
        <span class="chat-box mine">${text}</span>
    </div>`;
        $('.chat-content').append(template).scrollTop($('.chat-content')[0].scrollHeight);
        $('#prompt').val("");
        this.answer(text);
    },
}

main.init();