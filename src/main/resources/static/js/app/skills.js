var skills = {
    init: function () {
        let _this = this;

        $('#img-frontend').on('mouseover', function () {
            _this.over_img_frontend();
        });

        $('#img-frontend').on('mouseout', function () {
            _this.out_img_frontend();
        });
    },

    over_img_frontend: function () {
        $('#img-frontend').attr("class","img-size-big");
    },

    out_img_frontend: function () {
        $('#img-frontend').attr("class","img-size-middle");
    }
}

skills.init();