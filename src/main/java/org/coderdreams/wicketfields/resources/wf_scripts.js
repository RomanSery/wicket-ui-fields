"use strict";

let wicketUiFieldScripts = {
	initClientSideScripts: function () {
		//fix for select2 issue on firefox
		$.fn.modal.Constructor.prototype.enforceFocus = function() {};

		wicketUiFieldScripts.initSelect2();
		wicketUiFieldScripts.initPopoverScripts();
		wicketUiFieldScripts.initDatepicker();
		wicketUiFieldScripts.initCurrencyScripts();

		$(".txt-char-counter").each(function( index ) {
			wicketUiFieldScripts.initCharacterCounter($(this).attr('maxlength'), $(this).attr('count-msg-id'), $(this).attr('id'));
		});

		$('span.select2-selection--multiple').addClass('col-xs-10');
	},

	initPopoverScripts: function () {
		$('[data-rel=tooltip]').tooltip();
		$('[data-rel=popover]').popover({html:true, placement:'bottom'});

		$('[data-rel=popover-body]').popover({
			html:true, placement:'right', container: 'body',
			template: '<div class="popover app-popover-fw" role="popover" style="width: 700px; font-size: 95%;">' +
				'<div class="arrow"></div>'+
				'<h3 class="popover-title"></h3>'+
				'<div class="popover-content"></div>'+
				'</div>'
		});

		$('[data-rel=popover-body-md]').popover({
			html:true, placement:'bottom', container: 'body',
			template: '<div class="popover app-popover-fw" role="popover" style="width: 500px;">' +
				'<div class="arrow"></div>'+
				'<h3 class="popover-title"></h3>'+
				'<div class="popover-content"></div>'+
				'</div>'
		});
	},

	initCurrencyScripts: function () {
		$('.dollar-amt').inputmask("numeric", {
			radixPoint: ".", enforceDigitsOnBlur:true, groupSeparator: ",",
			digits: 2, integerDigits: 9, autoGroup: true, rightAlign: false, 'min': 0.0,
		});

		$('.dollar-amt-s').inputmask("numeric", {
			radixPoint: ".", groupSeparator: ",", enforceDigitsOnBlur:true, digits: 2, integerDigits: 9,
			autoGroup: true, prefix: '$', rightAlign: false, 'min': 0.0,
		}).focus(function() {
			if (this.value === '$0') {
				$(this).val('');
			}
		});

		$('.dollar-amt-s-allow-neg').inputmask("numeric", {
			radixPoint: ".", groupSeparator: ",", enforceDigitsOnBlur:true, digits: 2,
			integerDigits: 9, autoGroup: true, prefix: '$', rightAlign: false,
			oncleared: function () { self.Value(''); }
		}).focus(function() {
			if (this.value === '$0') {
				$(this).val('');
			}
		});


		$('.input-mask-phone').mask('(999) 999-9999');

		$('.numeric-only').numeric("positiveInteger");

		$('.percentage-field').inputmask("numeric", {
			radixPoint: ".", digits: 4, rightAlign: false, 'min': 0.0, 'max': 100.0,
			oncleared: function () { self.Value(''); }
		});

		$('.percentage-field-2').inputmask("numeric", {
			radixPoint: ".", digits: 4, rightAlign: false, 'min': 0.0,
			oncleared: function () { self.Value(''); }
		});

	},
	initSelect2: function () {
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
					let self = $(this);
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

	},

	initDatepicker: function () {

		$('.date-picker').datetimepicker({
			showClear: true,
			format: 'L'
		});

		$('.date-time-picker').datetimepicker( {
			showClear: true
		});

		$('.date-time-only-picker').datetimepicker({
			showClear: true,
			format: 'LT'
		});

		$('.date-month-only-picker').datetimepicker({
			showClear: true,
			format: 'MM/DD'
		});

	},

	initCharacterCounter: function (maxLength, countMsgId, fieldId) {
		$('#' + countMsgId).html(wicketUiFieldScripts.getNumCharsRemaining(maxLength, fieldId) + ' remaining');

		$('#'+fieldId).keyup(function() {
			$('#' + countMsgId).html(wicketUiFieldScripts.getNumCharsRemaining(maxLength, fieldId) + ' remaining');
		});
	},

	getNumCharsRemaining: function (maxLength, fieldId) {
		if ( $('#'+fieldId).length ){
			const text_length = $('#'+fieldId).val().length;
			let text_remaining = maxLength - text_length;
			if(text_remaining < 0) text_remaining = 0;
			return text_remaining;
		}
		return '';
	}
};