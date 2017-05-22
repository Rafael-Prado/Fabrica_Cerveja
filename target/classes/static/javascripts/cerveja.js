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

$(function() {
	var maskMoney = new Cerveja.MaskMoney();
	maskMoney.enable();
})

