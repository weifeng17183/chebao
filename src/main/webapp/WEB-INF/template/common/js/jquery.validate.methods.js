/**
 * jQuery Validation Plugin 1.8.0
 *
 * http://bassistance.de/jquery-plugins/jquery-plugin-validation/
 * http://docs.jquery.com/Plugins/Validation
 *
 * Copyright (c) 2006 - 2011 Jörn Zaefferer
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */
(function(){function a(b){return b.replace(/<.[^<>]*?>/g," ").replace(/&nbsp;|&#160;/gi," ").replace(/[0-9.(),;:!?%#$'"_+=\/-]*/g,"")}jQuery.validator.addMethod("maxWords",function(b,c,d){return this.optional(c)||a(b).match(/\b\w+\b/g).length<d},jQuery.validator.format("Please enter {0} words or less."));jQuery.validator.addMethod("minWords",function(b,c,d){return this.optional(c)||a(b).match(/\b\w+\b/g).length>=d},jQuery.validator.format("Please enter at least {0} words."));jQuery.validator.addMethod("rangeWords",
function(b,c,d){return this.optional(c)||a(b).match(/\b\w+\b/g).length>=d[0]&&b.match(/bw+b/g).length<d[1]},jQuery.validator.format("Please enter between {0} and {1} words."))})();jQuery.validator.addMethod("letterswithbasicpunc",function(a,b){return this.optional(b)||/^[a-z-.,()'\"\s]+$/i.test(a)},"Letters or punctuation only please");jQuery.validator.addMethod("alphanumeric",function(a,b){return this.optional(b)||/^\w+$/i.test(a)},"Letters, numbers, spaces or underscores only please");
jQuery.validator.addMethod("lettersonly",function(a,b){return this.optional(b)||/^[a-z]+$/i.test(a)},"Letters only please");jQuery.validator.addMethod("nowhitespace",function(a,b){return this.optional(b)||/^\S+$/i.test(a)},"No white space please");jQuery.validator.addMethod("ziprange",function(a,b){return this.optional(b)||/^90[2-5]\d\{2}-\d{4}$/.test(a)},"Your ZIP-code must be in the range 902xx-xxxx to 905-xx-xxxx");
jQuery.validator.addMethod("integer",function(a,b){return this.optional(b)||/^-?\d+$/.test(a)},"A positive or negative non-decimal number please");
jQuery.validator.addMethod("vinUS",function(a){if(a.length!=17)return false;var b,c,d,f,e,g=["A","B","C","D","E","F","G","H","J","K","L","M","N","P","R","S","T","U","V","W","X","Y","Z"],i=[1,2,3,4,5,6,7,8,1,2,3,4,5,7,9,2,3,4,5,6,7,8,9],j=[8,7,6,5,4,3,2,10,0,9,8,7,6,5,4,3,2],h=0;for(b=0;b<17;b++){f=j[b];d=a.slice(b,b+1);if(b==8)e=d;if(isNaN(d))for(c=0;c<g.length;c++){if(d.toUpperCase()===g[c]){d=i[c];d*=f;if(isNaN(e)&&c==8)e=g[c];break}}else d*=f;h+=d}a=h%11;if(a==10)a="X";if(a==e)return true;return false},
"The specified vehicle identification number (VIN) is invalid.");jQuery.validator.addMethod("dateITA",function(a,b){var c=false;if(/^\d{1,2}\/\d{1,2}\/\d{4}$/.test(a)){var d=a.split("/");c=parseInt(d[0],10);var f=parseInt(d[1],10);d=parseInt(d[2],10);var e=new Date(d,f-1,c);c=e.getFullYear()==d&&e.getMonth()==f-1&&e.getDate()==c?true:false}else c=false;return this.optional(b)||c},"Please enter a correct date");
jQuery.validator.addMethod("dateNL",function(a,b){return this.optional(b)||/^\d\d?[\.\/-]\d\d?[\.\/-]\d\d\d?\d?$/.test(a)},"Vul hier een geldige datum in.");jQuery.validator.addMethod("time",function(a,b){return this.optional(b)||/^([01][0-9])|(2[0123]):([0-5])([0-9])$/.test(a)},"Please enter a valid time, between 00:00 and 23:59");
jQuery.validator.addMethod("phoneUS",function(a,b){a=a.replace(/\s+/g,"");return this.optional(b)||a.length>9&&a.match(/^(1-?)?(\([2-9]\d{2}\)|[2-9]\d{2})-?[2-9]\d{2}-?\d{4}$/)},"Please specify a valid phone number");jQuery.validator.addMethod("phoneUK",function(a,b){return this.optional(b)||a.length>9&&a.match(/^(\(?(0|\+44)[1-9]{1}\d{1,4}?\)?\s?\d{3,4}\s?\d{3,4})$/)},"Please specify a valid phone number");
jQuery.validator.addMethod("mobileUK",function(a,b){return this.optional(b)||a.length>9&&a.match(/^((0|\+44)7(5|6|7|8|9){1}\d{2}\s?\d{6})$/)},"Please specify a valid mobile number");jQuery.validator.addMethod("strippedminlength",function(a,b,c){return jQuery(a).text().length>=c},jQuery.validator.format("Please enter at least {0} characters"));
jQuery.validator.addMethod("email2",function(a,b){return this.optional(b)||/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i.test(a)},jQuery.validator.messages.email);
jQuery.validator.addMethod("url2",function(a,b){return this.optional(b)||/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)*(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(a)},
jQuery.validator.messages.url);
jQuery.validator.addMethod("creditcardtypes",function(a,b,c){if(/[^0-9-]+/.test(a))return false;a=a.replace(/\D/g,"");b=0;if(c.mastercard)b|=1;if(c.visa)b|=2;if(c.amex)b|=4;if(c.dinersclub)b|=8;if(c.enroute)b|=16;if(c.discover)b|=32;if(c.jcb)b|=64;if(c.unknown)b|=128;if(c.all)b=255;if(b&1&&/^(51|52|53|54|55)/.test(a))return a.length==16;if(b&2&&/^(4)/.test(a))return a.length==16;if(b&4&&/^(34|37)/.test(a))return a.length==15;if(b&8&&/^(300|301|302|303|304|305|36|38)/.test(a))return a.length==14;if(b&
16&&/^(2014|2149)/.test(a))return a.length==15;if(b&32&&/^(6011)/.test(a))return a.length==16;if(b&64&&/^(3)/.test(a))return a.length==16;if(b&64&&/^(2131|1800)/.test(a))return a.length==15;if(b&128)return true;return false},"Please enter a valid credit card number.");
jQuery.validator.addMethod("ipv4",function(a,b){return this.optional(b)||/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/i.test(a)},"Please enter a valid IP v4 address.");
jQuery.validator.addMethod("ipv6",function(a,b){return this.optional(b)||/^((([0-9A-Fa-f]{1,4}:){7}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}:[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){5}:([0-9A-Fa-f]{1,4}:)?[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){4}:([0-9A-Fa-f]{1,4}:){0,2}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){3}:([0-9A-Fa-f]{1,4}:){0,3}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){2}:([0-9A-Fa-f]{1,4}:){0,4}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){6}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(([0-9A-Fa-f]{1,4}:){0,5}:((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|(::([0-9A-Fa-f]{1,4}:){0,5}((\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b)\.){3}(\b((25[0-5])|(1\d{2})|(2[0-4]\d)|(\d{1,2}))\b))|([0-9A-Fa-f]{1,4}::([0-9A-Fa-f]{1,4}:){0,5}[0-9A-Fa-f]{1,4})|(::([0-9A-Fa-f]{1,4}:){0,6}[0-9A-Fa-f]{1,4})|(([0-9A-Fa-f]{1,4}:){1,7}:))$/i.test(a)},"Please enter a valid IP v6 address.");
jQuery.validator.addMethod("alphanumeric", function(value, element){return this.optional(element) || /^\w+$/i.test(value);},"Letters, numbers, spaces or underscores only please");
jQuery.validator.addMethod("specialChar",function(value, element){return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);},"只允许包含中文、英文、数字和下划线!");
jQuery.validator.addMethod("username",function(value, element){return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);},"只允许包含中文、英文、数字和下划线!");
jQuery.validator.addMethod("username1",function(value, element){return this.optional(element) || /^[_\.0-9A-Za-z-]+@([0-9A-Za-z][0-9A-Za-z-]+\.)+[A-Za-z]{2,4}$/.test(value);},"请输入正确的用户名(邮箱)！");
jQuery.validator.addMethod("requiredTo",function(value, element, param){var requiredTo = $(param).val();if(requiredTo != "") {return value != "";} else {return true;}}, "此内容为必填项,请输入");
jQuery.validator.addMethod("requiredOne", function(value, element, param) {var requiredOne = $(param).val();if(requiredOne == "") {return value != "";} else {return true;};}, $.validator.format("两项必须填写一项"));
jQuery.validator.addMethod("notEqual", function(value, element, param) {return value != param;}, $.validator.format("输入值不允许为{0}"));
jQuery.validator.addMethod("positive", function(value, element) {return this.optional(element) || value > 0;}, "只允许输入整数");
jQuery.validator.addMethod("positiveInteger",function(value, element){return this.optional(element) || /^[1-9]\d*$/.test(value);}, "只允许输入正整数");
jQuery.validator.addMethod("imageFile",function(value, element){return this.optional(element) || /(.jpg|.jpeg|.gif|.bmp|.png)$/i.test(value);}, "图片文件格式错误");
jQuery.validator.addMethod("excelFile",function(value, element){return this.optional(element) || /(.xls|.xlsx)$/i.test(value);}, "Excel文件格式错误");
jQuery.validator.addMethod("handset",function(value,element){return this.optional(element) || /^1(3|4|5|8)[0-9]{9}$/.test(value);},"手机号码错误！");

jQuery.validator.addMethod("mobile",function(value,element){return this.optional(element) || /^[0-9\/\-]*$/.test(value);},"电话号码错误！");

jQuery.validator.addMethod("postfix",function(value,element){return this.optional(element) || /^([0-9A-Za-z-]+\.[0-9A-Za-z]+\.)|([0-9A-Za-z-]+\.)+[A-Za-z]{2,4}$/.test(value);},"邮箱后缀格式不正确！");
jQuery.validator.addMethod("companyNumber",function(value,element){return this.optional(element) || /^[1-9]\d{0,6}$/.test(value);},"公司编号格式不正确！");
jQuery.validator.addMethod("spellName",function(value,element){return this.optional(element) || /^[A-Za-z()\s,.!@#$%]+$/.test(value);},"公司拼音格式不正确！");
jQuery.validator.addMethod("englishName",function(value,element){return this.optional(element) || /^[A-Za-z()\s,.!@#$%]+$/.test(value);},"公司英文名格式不正确！");
jQuery.validator.addMethod("procedureRateName",function(value,element){if(this.optional(element) || /^[0-9]+\.{0,1}[0-9]{0,3}$/.test(value) && value>4){return true;}else{return false;}},"手续费率只能输入数字且大于4！");
jQuery.validator.addMethod("deliveryFee",function(value,element){return this.optional(element) || /^(0|[1-9][0-9]*)$/.test(value);},"物流费用必须大于0！");
jQuery.validator.addMethod("deliverySn",function(value,element){return this.optional(element) || /^[0-9]*$/.test(value);},"只能填写数字！");
jQuery.validator.addMethod("decimals",function(value,element){return this.optional(element) || /^(0\.[1-9]\d*|[1-9]\d*(\.\d+)?)$/.test(value);},"只能填写正确的货币格式！");
jQuery.validator.addMethod("decimals2",function(value,element){return this.optional(element) || /^[\+]?\d+(\.d+)?$/.test(value);},"请填写大于等于0的正整数或者小数！");