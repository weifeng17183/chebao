(function ($) {
	var methods = {
		ratio : function (args) {
			var item = args.item,
				settings = args.settings;
			return { w : item.width()/settings.wrapperWidth, 
					h : item.height()/settings.wrapperHeight };
		},
		center : function (longVal, shortVal) {
			return parseInt((longVal-shortVal)/2, 10);
		},
		scaleToFill : function (args) {
			var item = args.item,
			settings = args.settings,
			ratio = settings.ratio,
			width = item.width(),
			height = item.height(),
			offset = {top: 0, left: 0};
			if (ratio.h >= ratio.w) {	//如果高比例大于宽比例
				width = settings.wrapperWidth;
				height = height / ratio.w;
				if (settings.center) {
					offset.top = methods.center(height, 80);
				}
			} else {
				height = settings.wrapperHeight;
				width = width / ratio.h;
				if (settings.center) {
					offset.left = methods.center(100, width);
				}
			}
			
			if (settings.center) {
				args.wrapper.css('position', 'relative');
				item.css({
					'position' : 'absolute',
					'top' : ['-', offset.top, 'px'].join(''),
					'left' : offset.left + 'px'
				});
			}
			
			return item.height(height).attr('height', height + 'px')
						.width(width).attr('width', width + 'px');
		},
		scaleToFit : function (args) {
			var item = args.item,
				settings = args.settings,
				ratio = settings.ratio,
				width = item.width(),
				height = item.height(),
				offset = {top: 0, left: 0};
			
			if (ratio.h >= ratio.w) {
				height = settings.wrapperHeight,
				width = parseInt((item.width() * settings.wrapperHeight)/item.height(), 10);
				if (settings.center) {
					offset.left = methods.center(height, width);
				}
			} else {
				height = parseInt((item.height() * settings.wrapperWidth)/item.width(), 10),
				width = settings.wrapperWidth;
				if (settings.center) {
					offset.top = methods.center(width, height);
				}
			}

			args.wrapper.css({
				'width' : (settings.squareWidth ? settings.wrapperWidth : width) + 'px',
				'height' : settings.wrapperHeight + 'px'
			});
			
			if (settings.center) {
				args.wrapper.css('position', 'relative');
				item.css({
					'position' : 'absolute',
					'top' : offset.top +'px',
					'left' : offset.left + 'px'
				});
			}
			return item.height(height).attr('height', height + 'px')
						.width(width).attr('width', width + 'px');
		},
		init : function (options) {
			var settings = $.extend({
				wrapperSelector : null,
				wrapperWidth : 100,
				wrapperHeight : 80,
				center : true,
				fill : true,
				initClass : 'fc-init',
				doneEvent : 'fakedropdone',
				squareWidth : true
			}, options),
			_init = function () {
				var item = $(this),
					wrapper = settings.wrapperSelector ? item.closest(settings.wrapperSelector) : item.parent(),
					args = { item : item,
							settings : settings,
							wrapper : wrapper }; 
					settings.ratio = methods.ratio(args);
					if (settings.fill) {
						wrapper.css({
							'overflow' : 'hidden',
							'width' : settings.wrapperWidth + 'px',
							'height' : settings.wrapperHeight + 'px'
						});
						methods.scaleToFill(args);
					} else {
						methods.scaleToFit(args);
					}
					
					item.data('fc.settings', settings)
						.addClass(settings.initClass) // Add class to container after initialization
						.trigger(settings.doneEvent); // Publish an event to announce that fakecrop in initialized
			},
			images = this.filter('img'),
			others = this.filter(':not(img)');
			if (images.length) {
				images.bind('load', function () {
					_init.call(this);
					this.style.display = 'inline';
				}).each(function () {
					if (this.complete || this.complete === undefined) {
						var src = this.src;
						this.src = "data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///ywAAAAAAQABAAACAUwAOw==";
						this.src = src;
						this.style.display = 'none';
					}
				});
			}
			if (others.length) {
				others.each(_init);
			}
			return this;
		}	
	};
	$.fn.fakecrop = function (method) {
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.fakecrop');
		} 
	};
})(jQuery);
