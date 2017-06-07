var Cerveja  = Cerveja ||{};

Cerveja.MascaraCpfCnpj = (function(){
	function MascaraCpfCnpj(){
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelCpfCnpj = $('[for=cpfcnpj]');
		this.inputCpfCnpj = $('#cpfcnpj');
	}
	
	MascaraCpfCnpj.prototype.iniciar = function(){
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));
	}
	
	function onTipoPessoaAlterado(evento){
		var tipoPessoaSelecionada = $(evento.currentTarget);
		this.labelCpfCnpj.text(tipoPessoaSelecionada.data('documento'));
		this.inputCpfCnpj.mask(tipoPessoaSelecionada.data('mascara'));
		this.inputCpfCnpj.val('');
		this.inputCpfCnpj.removeAttr('disabled');
		
	}
	
	
	return MascaraCpfCnpj;
}());

$(function(){
	var mascaraCpfCnpj = new Cerveja.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
});