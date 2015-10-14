/**
 * form表单自动填充json数据
 * 用法：$('#form').autoSetFormData(row);
 */
(function($) {
	$.fn.autoSetFormData = function(obj) {
		// var obj = eval("(" + jsonStr + ")");
		var key, value, tagName, type, arr;
		for (x in obj) {
			key = x;
			value = obj[x];
			$("[name='" + key + "'],[name='" + key + "[]']", this).each(function() {
				tagName = $(this)[0].tagName;
				type = $(this).attr('type');
				if (tagName == 'INPUT') {
					if (type == 'radio') {
						$(this).attr('checked', $(this).val() == value);
					} else if (type == 'checkbox') {
						arr = value.split(',');
						for (var i = 0; i < arr.length; i++) {
							if ($(this).val() == arr[i]) {
								$(this).attr('checked', true);
								break;
							}
						}
					} else {
						$(this).val(value);
					}
				} else if (tagName == 'SELECT' || tagName == 'TEXTAREA') {
					if (tagName == 'SELECT') {
						value = "" + value;
					}
					$(this).val(value);
				}

			});
		}
	};
})(jQuery);

/**
 * 定义centerLayout常量
 */
var centerHeight;

function resetPanel(treeNode) {
	var tab_id = "tab_" + treeNode.id;
	if (treeNode.is_leaf == 1) {
		if (!navtab.isTabItemExist(tab_id)) {
			navtab.addTabItem({
				url : treeNode.menuUrl,
				height : centerHeight,
				tabid : tab_id,
				text : treeNode.name
			});
		} else {
			navtab.removeTabItem(tab_id);
			navtab.addTabItem({
				url : treeNode.menuUrl,
				height : centerHeight,
				tabid : tab_id,
				text : treeNode.name
			});
		}
	}
};

/**
 * 校验URL的合法性
 * @param single
 * @param inputElement
 * @returns {boolean}
 */
function checkUrl(url){
	var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
		+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
		+ "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
		+ "|" // 允许IP和DOMAIN（域名）
		+ "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
		+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
		+ "[a-z]{2,6})" // first level domain- .com or .museum
		+ "(:[0-9]{1,4})?" // 端口- :80
		+ "((/?)|" // a slash isn't required if there is no file name
		+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";

	var re = new RegExp(strRegex);
	return re.test(url);
}

