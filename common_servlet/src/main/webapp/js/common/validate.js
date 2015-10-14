// 校验是否为空
function isNull(str) {
	if (str == "")
		return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}

// 校验是否是数字
function isNumber(strNum) {
	if (isNull(strNum))
		return false;
	var re = /^-?[1-9]+(\.\d+)?$|^-?0(\.\d+)?$|^-?[1-9]+[0-9]*(\.\d+)?$/;
	return re.test(strNum);
}

// 校验是否是正整数
function isPositiveInteger(strNum) {
	if (isNull(strNum))
		return false;
	var re = /^[0-9]*[1-9][0-9]*$/;
	return re.test(strNum);
}

// 校验ip地址
function isIP(strIP) {
	if (isIPV4(strIP) || isIPV6(strIP))
		return true;
	return false;
}

// 校验ip v4地址
function isIPV4(strIP) {
	if (isNull(strIP))
		return false;
	var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g
	if (re.test(strIP)) {
		if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256)
			return true;
	}
	return false;
}

// 校验ip v6地址
function isIPV6(strIP) {
	if (isNull(strIP))
		return false;
	var re = /^\s*((([0-9A-Fa-f]{1,4}:){7}(([0-9A-Fa-f]{1,4})|:))|(([0-9A-Fa-f]{1,4}:){6}(:|((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})|(:[0-9A-Fa-f]{1,4})))|(([0-9A-Fa-f]{1,4}:){5}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){4}(:[0-9A-Fa-f]{1,4}){0,1}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){3}(:[0-9A-Fa-f]{1,4}){0,2}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:){2}(:[0-9A-Fa-f]{1,4}){0,3}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(([0-9A-Fa-f]{1,4}:)(:[0-9A-Fa-f]{1,4}){0,4}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(:(:[0-9A-Fa-f]{1,4}){0,5}((:((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})?)|((:[0-9A-Fa-f]{1,4}){1,2})))|(((25[0-5]|2[0-4]\d|[01]?\d{1,2})(\.(25[0-5]|2[0-4]\d|[01]?\d{1,2})){3})))(%.+)?\s*$/;
	return re.test(strIP);
}

// 校验ip v4地址段：后一个IP的各位均不小于前一个IP
function isIpRange(prevIP, nextIP) {
	if (!isIPV4(prevIP) || !isIPV4(nextIP)) {
		return false;
	}
	var sign = ".";
	var prevArray = prevIP.split(sign);
	var nextArray = nextIP.split(sign);
	var result = true;
	for (var i = 0; i < prevArray.length; i++) {
		if (parseInt(nextArray[i]) < parseInt(prevArray[i])) {
			result = false;
			break;
		}
	}
	return result;
}

var VALIDATE={
		
		REGEX_VALIDATE_HOSTNAME :   /^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\.[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?)*$/,
		
		isValidHostname : function (str) {
			var match = str.match(this.REGEX_VALIDATE_HOSTNAME);
			if (match === null || match[0] !== str) {
				return false;
			}
			else {
		        var arry = str.split(".");
		        //non dotted numbers or strings are valid hostnames
		        if (arry.length == 1) {
		        	return true;
		        }
		        var i, valid = false;
		        for (i=0; i<arry.length; i++) {
		            // if all of the subsections are purely numeric then it is not a valid hostname
		            if (!this.isNumeric(arry[i]) ) {
		            	valid = true;
		            }
		        }
		        return valid;
		    }
		},
		
		isNumeric : function (value) {
		    var i, validNumber = "0123456789";
		    for (i=0; i<value.length; i++) {
		        var Char = value.charAt(i);
		        if (validNumber.indexOf(Char) == -1) {
		            return false;
		        }
		    }
		    return true;
		},
		isIpv4OnlyAddress:function(strIp)
		{
			if(strIp.length>0)
				{
				 	if(isIP(strIp))
				 		{
				 		if	(strIp == "0.0.0.0") { return false; }
					      if(strIp == "255.255.255.255") { return false; }
					      return true;
				 		}
				 	
				}
			return false;
		},
		

		validUserNameWithDomainDB:function (username)
		{
			var invalidChars = ":*?\"<|>;,&%#!^&$%()+'=~`{}";
			for(i=0; i<invalidChars.length ;i++){
				badChar = invalidChars.charAt(i)
				if(username.indexOf(badChar,0) > -1){
					return false;
					}
				}
			return true;
		}
		
		
}



