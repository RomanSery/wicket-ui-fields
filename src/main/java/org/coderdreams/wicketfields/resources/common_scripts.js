

function initClientSideScripts() {
	//fix for select2 issue on firefox
	$.fn.modal.Constructor.prototype.enforceFocus = function() {};

	initSelect2();
	initPopoverScripts();
	initDatepicker();
	initDateTimepicker();
	initCurrencyScripts();

	$(".txt-char-counter").each(function( index ) {
		initCharacterCounter($(this).attr('maxlength'), $(this).attr('count-msg-id'), $(this).attr('id'));
	});

	$('span.select2-selection--multiple').addClass('col-xs-10');
}

function initPopoverScripts() {
	$('[data-rel=tooltip]').tooltip();
	$('[data-rel=popover]').popover({html:true, placement:'bottom'});

	$('[data-rel=popover-body]').popover({
		html:true,
		placement:'right',
		container: 'body',
		template: '<div class="popover app-popover-fw" role="popover" style="width: 700px; font-size: 95%;">' +
			'<div class="arrow"></div>'+
			'<h3 class="popover-title"></h3>'+
			'<div class="popover-content"></div>'+
			'</div>'
	});

	$('[data-rel=popover-body-md]').popover({
		html:true,
		placement:'bottom',
		container: 'body',
		template: '<div class="popover app-popover-fw" role="popover" style="width: 500px;">' +
			'<div class="arrow"></div>'+
			'<h3 class="popover-title"></h3>'+
			'<div class="popover-content"></div>'+
			'</div>'
	});
}
function initCurrencyScripts() {
    $('.dollar-amt').inputmask("numeric", {
        radixPoint: ".",
        enforceDigitsOnBlur:true,
        groupSeparator: ",",
        digits: 2,
        integerDigits: 9,
        autoGroup: true,
        rightAlign: false,
        'min': 0.0,
    });

    $('.dollar-amt-s').inputmask("numeric", {
        radixPoint: ".",
        groupSeparator: ",",
        enforceDigitsOnBlur:true,
        digits: 2,
        integerDigits: 9,
        autoGroup: true,
        prefix: '$', //No Space, this will truncate the first character
        rightAlign: false,
        'min': 0.0,
    });
    $('.dollar-amt-s').focus(function() {
        if (this.value === '$0') {
            $(this).val('');
        }
    });

    $('.dollar-amt-s-allow-neg').inputmask("numeric", {
        radixPoint: ".",
        groupSeparator: ",",
        enforceDigitsOnBlur:true,
        digits: 2,
        integerDigits: 9,
        autoGroup: true,
        prefix: '$', //No Space, this will truncate the first character
        rightAlign: false,
        oncleared: function () { self.Value(''); }
    });
    $('.dollar-amt-s-allow-neg').focus(function() {
        if (this.value === '$0') {
            $(this).val('');
        }
    });

    $('.input-mask-phone').mask('(999) 999-9999');

    $('.numeric-only').numeric("positiveInteger");

    $('.percentage-field').inputmask("numeric", {
        radixPoint: ".",
        digits: 4,
        rightAlign: false,
        'min': 0.0,
        'max': 100.0,
        oncleared: function () {
            self.Value('');
        }
    });

    $('.percentage-field-2').inputmask("numeric", {
        radixPoint: ".",
        digits: 4,
        rightAlign: false,
        'min': 0.0,
        oncleared: function () {
            self.Value('');
        }
    });

}
function initSelect2() {
	$("select.wicket-ui-select2").select2({
		placeholder: 'Select one',
		allowClear: true
	});

	$("select.wicket-ui-select2-noclear").select2({
		placeholder: 'Select one',
		allowClear: false
	});

	//select2 clear-button work-around, until they fix it
	$('select').on('select2:unselecting', function(ev) {
		if (ev.params.args.originalEvent) {
			ev.params.args.originalEvent.stopPropagation();
		} else {
			$("select").each(function( index ) {
				if ($(this).hasClass("select2-hidden-accessible") && $(this).data('select2').isOpen()) {
					$(this).select2('close');
				}
			});
			$(this).one('select2:opening', function(ev) {
				ev.preventDefault();
				var self = $(this);
				setTimeout(function() {
					self.select2('close');
				}, 1);
			});
		}
	});

	//open select2 on tab
	$(document).on('focus', '.select2.select2-container', function (e) {
		if (e.originalEvent && $(this).find(".select2-selection--single").length > 0) {
			$(this).siblings('select:enabled').select2('open')
		}
	});

}
function initDatepicker() {
	let vertPos;

	$('.date-picker').each(function(index){
		if($(this).parents(".modal").length > 0){
			vertPos = 'bottom';
		} else {
			vertPos = 'auto';
		}

		$(this).datetimepicker({
			showClear: true,
			widgetPositioning: {
				horizontal: 'auto',
				vertical: vertPos
			},
			icons: {
				time: 'fa fa-clock-o',
				date: 'fa fa-calendar',
				up: 'fa fa-chevron-up',
				down: 'fa fa-chevron-down',
				previous: 'fa fa-chevron-left',
				next: 'fa fa-chevron-right',
				today: 'fa fa-arrows ',
				clear: 'fa fa-trash',
				close: 'fa fa-times'
			},
			format: 'MM/DD/YYYY'
		}).on("dp.change", function (e) {
			var d = new Date(e.date);
			if (d.getFullYear() < 1000) {
				d.setFullYear(d.getFullYear() + 2000);
				$(this).data("DateTimePicker").date(d);
			}
		});
	});

}
function initDateTimepicker() {
	$('.date-time-picker').datetimepicker({
		showClear: true,
		icons: {
			time: 'fa fa-clock-o',
			date: 'fa fa-calendar',
			up: 'fa fa-chevron-up',
			down: 'fa fa-chevron-down',
			previous: 'fa fa-chevron-left',
			next: 'fa fa-chevron-right',
			today: 'fa fa-arrows ',
			clear: 'fa fa-trash',
			close: 'fa fa-times'
		}
	});
	$('.date-time-picker').on('dp.change', function(e) {
		var d = new Date(e.date);
		if (d.getFullYear() < 1000) {
			d.setFullYear(d.getFullYear() + 2000);
			$(this).data("DateTimePicker").date(d);
		}
		if (e.oldDate === null) {
			$(this).data('DateTimePicker').date(new Date());
		}
	});


	$('.date-time-only-picker').datetimepicker({
		showClear: true,
		toolbarPlacement: 'bottom',
		icons: {
			time: 'fa fa-clock-o',
			date: 'fa fa-calendar',
			up: 'fa fa-chevron-up',
			down: 'fa fa-chevron-down',
			previous: 'fa fa-chevron-left',
			next: 'fa fa-chevron-right',
			today: 'fa fa-arrows ',
			clear: 'fa fa-trash',
			close: 'fa fa-times'
		}, format: 'LT'
	});


	$('.date-month-only-picker').datetimepicker({
		showClear: true,
		icons: {
			time: 'fa fa-clock-o',
			date: 'fa fa-calendar',
			up: 'fa fa-chevron-up',
			down: 'fa fa-chevron-down',
			previous: 'fa fa-chevron-left',
			next: 'fa fa-chevron-right',
			today: 'fa fa-arrows ',
			clear: 'fa fa-trash',
			close: 'fa fa-times'
		}, format: 'MM/DD'
	});
}


function initCharacterCounter(maxLength, countMsgId, fieldId) {
	$('#' + countMsgId).html(getNumCharsRemaining(maxLength, fieldId) + ' remaining');

	$('#'+fieldId).keyup(function() {
	  $('#' + countMsgId).html(getNumCharsRemaining(maxLength, fieldId) + ' remaining');
	});
}

function getNumCharsRemaining(maxLength, fieldId) {
	if ( $('#'+fieldId).length ){
		var text_length = $('#'+fieldId).val().length;
    	var text_remaining = maxLength - text_length;
    	if(text_remaining < 0) text_remaining = 0;
    	return text_remaining;
	}
	return '';
}









