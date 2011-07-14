

function scrollChatToBottom() {
	var chatLog = jQuery("#formChat\\:psChatMsgs");
	chatLog.animate({scrollTop : chatLog.attr("scrollHeight") - chatLog.height()}, 80);
	return;
}