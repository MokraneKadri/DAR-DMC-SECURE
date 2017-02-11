var notify = function (notification, notifier) {

	window.console.log(notification);

	var container = document.createElement("div");

	if (notification.success != undefined) {
		$(container).html(notification.success);
		$(container).addClass("success");
	} else if (notification.warning != undefined) {
		$(container).html(notification.warning);
		$(container).addClass("warning");
	} else if (notification.error != undefined) {
		$(container).html(notification.error);
		$(container).addClass("error");
	}

	setTimeout(function () {
		$(container).remove();
	}, 3000);

	$(notifier).append(container);
}