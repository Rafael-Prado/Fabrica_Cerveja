/* Modularizando JavaScript*/

/*Objeto representado nameSpace*/
var Cerveja = Cerveja || {};

/* Função Contrutora*/
Cerveja.MaskMoney =(function() {
	function MaskMoney() {
		/*Inicialização no contrutor*/
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	/*Execução comportamento*/
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney( {decimal: ',', thousands: '.'});
		this.plain.maskMoney({precision: 0, thousands: '.'});
	}
	return MaskMoney;	
	
})();

Cerveja.MaskPhoneNumber = (function(){
	function MaskPhoneNumber(){
	  this.inputPhoneNumber= $('.js-phone-number');
	}
	MaskPhoneNumber.prototype.enable= function(){
	   var maskBehavior = function (val) {
		 return val.replace(/\D/g, '').length === 11 ? '(00) 00000-0000' : '(00) 0000-00009';
		};
		
		var options = {
			onKeyPress: function(val, e, field, options) {
		     field.mask(maskBehavior.apply({}, arguments), options);
		  }
	   };
		this.inputPhoneNumber.mask(maskBehavior, options);
	}
	
	return MaskPhoneNumber;
}());

$(function() {
	var maskMoney = new Cerveja.MaskMoney();
	maskMoney.enable();
	
	var maskPhoneNamber = new Cerveja.MaskPhoneNumber();
	maskPhoneNamber.enable();
})

