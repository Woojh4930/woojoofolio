var skills = {
    init: function () {
        let _this = this;

        $('.img-size-middle').hover(function () {_this.over_img_frontend($(this));},
            function () {_this.out_img_frontend($(this));});
    },

    over_img_frontend: function (img) {
        img.attr("class","img-size-big");
        $('#skill-name').text(img.attr('alt'));
    },

    out_img_frontend: function (img) {
        img.attr("class","img-size-middle");
        $('#skill-name').text("");
    }
}

skills.init();