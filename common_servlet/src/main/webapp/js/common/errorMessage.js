var Message = {

	ERRORTYPE : {
		ERROR : "ERROR",
		INFO : "INFO",
		FATAL : "FATAL",
		WARNING : "WARNING"
	},
	ResponseMessage : function(type, message) {
		this.responseType = type;
		this.responseMessage = message;
		return this;
	},
	createNew : function() {
		var mes = {};
		mes.displayMessage = function(messageArray) {

			if (isArrayFn(messageArray)) {
				showMultipleMessage(messageArray);

			} else {
				if (messageArray != "" && messageArray != "undefined") {
					showSingleMessage(messageArray);
				}

			}
		}

		var isArrayFn = function(data) {
			return Object.prototype.toString.call(data) === '[object Array]';
		}
		var showMultipleMessage = function(messageArray) {
			var rightArray = new Array();
			var errorArray = new Array();

			for (var i = 0; i <= messageArray.length - 1; i++) {
				if (messageArray[i].responseType == "INFO") {
					rightArray.push(messageArray[i]);
				} else {
					errorArray.push(messageArray[i]);
				}
			}
			writeMessage(rightArray);
			writeMessage(errorArray);
		}
		var showSingleMessage = function(messageSingle) {
			var alertType = messageSingle.responseType;
			var message = messageSingle.responseMessage;
			var messageStyle = getMessageStyle(messageSingle);
			var clss = messageStyle.getClassName();
			var icon = messageStyle.getImgPath();
			$("#errorMessage")
					.html(
							"<div class="
									+ clss
									+ ">"
									+ "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"><tbody><tr><td><img id=\"pageErrorIconImage\""
									+ " src="
									+ icon
									+ " style=\"float:left; margin: 6px;\"></td>"
									+ "<td id=\"pageErrorTableContainerTextPlaceTD\" class=\"pageErrorTextPlace\" width=\"100%\"><p class=\"resultMessage\">"
									+ message + "</p></td></tr></tbody></table></div>");
		}
		var writeMessage = function(messageArray) {
			if (isArrayFn(messageArray) && messageArray.length > 1) {
				var div = document.createElement('div');
				var messageStyle = getMessageStyle(messageArray[0]);
				div.className = messageStyle.getClassName();
				var imgPath = messageStyle.getImgPath();
				var img = document.createElement('img');
				img.id = 'errormessageImg';
				img.src = imgPath;
				img.style.cssText = "float:left;margin:6px;";
				div.appendChild(img);
				var ul = document.createElement('ul');
				ul.style.cssText = "list-style:none outside disc";
				for (var i = 0; i <= messageArray.length - 1; i++) {
					var li = document.createElement('li');
					li.style.cssText = "margin:7px 10px 7px 60px";
					var textNode = document.createTextNode(messageArray[i].responseMessage);
					li.appendChild(textNode);
					ul.appendChild(li);
				}
				div.appendChild(ul);
				var messageDiv = document.getElementById("errorMessage");
				messageDiv.appendChild(div);
			} else if (isArrayFn(messageArray) && messageArray.length == 1) {
				showSingleMessage(messageArray[0]);

			}
		}
		var getMessageStyle = function(messageObject) {

			var className = "";
			var imgPath = "";
			if (messageObject.responseType == "INFO") {
				className = "successResult";
				imgPath = root_path + '/images/error/v-icon.gif';

			} else if (messageObject.responseType == "ERROR"
					|| messageObject.responseType == "FATAL") {
				className = "errorResult";
				imgPath = root_path + '/images/error/error.gif';
			} else if (messageObject.responseType == "WARNING") {
				className = "cautionResult";
				imgPath = root_path + '/images/error/warning.gif';
			}
			var messageStyle = MessageStyle.createNew();
			messageStyle.setClassName(className);
			messageStyle.setImgPath(imgPath);
			return messageStyle;
		}
		return mes;
	}
}

var MessageStyle = {

	createNew : function() {
		var mesStyle = {};
		var className = "";
		var imgPath = "";
		mesStyle.getClassName = function() {
			return className;
		};
		mesStyle.getImgPath = function() {
			return imgPath;
		};
		mesStyle.setClassName = function(name) {
			className = name;
		};
		mesStyle.setImgPath = function(img) {
			imgPath = img;
		};
		return mesStyle;
	}
}
