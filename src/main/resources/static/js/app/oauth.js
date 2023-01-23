var oauth = {
    init: function () {
        let _this = this;

        $('#btn-google').on("click", function () {
            _this.move("google");
        })

        $('#btn-google').on("mouseover", function () {
            _this.over();
        })

        $('#btn-google').on("mouseout", function () {
            _this.out();
        })

        $('#btn-naver').on("click", function () {
            _this.move("naver");
        })

        $('#btn-naver').on("mouseover", function () {
            _this.over_naver();
        })

        $('#btn-naver').on("mouseout", function () {
            _this.out_naver();
        })
    },

    move: function (address) {
        $('#btn-google').attr('src', '/images/google/btn_google_dark_pressed_ios.svg');
        location.href = "/oauth2/authorization/"+address;
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
    }
}

oauth.init();