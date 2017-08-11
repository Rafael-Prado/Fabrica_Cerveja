Cerveja = Cerveja || {};

Cerveja.MultiSelecao = (function(){
	function MultiSelecao(){
		this.statusBtn = $('.js-status-btn');
		this.selecaoCheckbox = $('.js-selecao');
		this.selecaoTodosCheckbox = $('.js-selecao-todos');
	}
	MultiSelecao.prototype.iniciar = function(){
		this.statusBtn.on('click', onStatusBtnClicado.bind(this));
		this.selecaoTodosCheckbox.on('click', onSelecaoTodosClicado.bind(this));
		this.selecaoCheckbox.on('click', onSelecaoClicado.bind(this));
	}
	
	function onStatusBtnClicado(event){
		var btnClicado = $(event.currentTarget);
		var status = btnClicado.data('status');
		var url = btnClicado.data('url');
		
		var checkboxSelecionado = this.selecaoCheckbox.filter(':checked');
		var codigos = $.map(checkboxSelecionado, function(c){
			return $(c).data('codigo');
		});
		
		if (codigos.length > 0) {
			$.ajax({
				url:url,
				method:'PUT',
				data: {
					codigos: codigos,
					status: status
				}, 
				success: function() {
					window.location.reload();
				}
			});
			
		}
		
	   }
	   function onSelecaoTodosClicado(){
		var status = this.selecaoTodosCheckbox.prop('checked');
		this.selecaoCheckbox.prop('checked', status);
		stastusBotaoAcao.call(this, status);
		
	   }
	   function onSelecaoClicado(){
		   var selecaoCehckboxChecados  = this.selecaoCheckbox.filter(':checked');
		   this.selecaoTodosCheckbox.prop('checked', selecaoCehckboxChecados.length >= this.selecaoCheckbox.length);
		   stastusBotaoAcao.call(this, selecaoCehckboxChecados.length);
	   }
	   
	   function stastusBotaoAcao(ativar){
		   ativar ? this.statusBtn.removeClass('disabled') : this.statusBtn.addClass('disabled');
	   }
	
	
	return MultiSelecao;
}());

$(function(){
	var multiSelecao = new Cerveja.MultiSelecao();
	multiSelecao.iniciar();
});
