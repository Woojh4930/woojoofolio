var oauth = {
    init: function () {
        let _this = this;

        $('#btn-google').on("click", function () {
            _this.move("google");
        });

        $('#btn-google').on("mouseover", function () {
            _this.over();
        });

        $('#btn-google').on("mouseout", function () {
            _this.out();
        });

        $('#btn-naver').on("click", function () {
            _this.move("naver");
        });

        $('#btn-naver').on("mouseover", function () {
            _this.over_naver();
        });

        $('#btn-naver').on("mouseout", function () {
            _this.out_naver();
        });

        $('#version').on('click', function () {
            _this.toggle();
        });

        $('#btn-skills').on('click')
    },

    move: function (address) {
        if (address === 'google') {
            $('#btn-google').attr('src', '/images/google/btn_google_dark_pressed_ios.svg');
        }
        location.href = "/oauth2/authorization/" + address;
    },

    over: function () {
        $('#btn-google').attr('src', '/images/google/btn_google_dark_focus_ios.svg');
    },

    over_naver: function () {
        $('#btn-naver').attr('class', 'btn-oauth naver-over size-40');
    },

    out: function () {
        $('#btn-google').attr('src', '/images/google/btn_google_dark_normal_ios.svg');
    },

    out_naver: function () {
        $('#btn-naver').attr('class', 'btn-oauth size-40');
    },

    toggle: function () {
        let skills = $('#btn-skills');

        if (skills.css("display") === 'none') {
            skills.show();
            return;
        }
        skills.hide();
    },


}

oauth.init();