//Modularizando js cadastro estilo rápido

var Cerveja = Cerveja ||{};

Cerveja.EstiloCadastroRapido = (function() {
	
	function EstiloCadastroRapido() {
		this.modal = $('#modalCadastroRapidoEstilo');
		this.botaoSalvar = this.modal.find('.js-modal-cadastro-estilo-salvar-btn');
		this.conteinerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
		this.form = this.modal.find('form');		
		//pegando a url atribuida ao action do form.
		this.url = this.form.attr('action');
		//pegando o input do form pelo id
		this.inputNomeEstilo = $('#nomeEstilo');
		
	}
	EstiloCadastroRapido.prototype.iniciar = function(){
		//função para não submeter o modal ao apertar o enter.	
		this.form.on('submit', function(event){event.preventDefault()});	
		//Pegando o evento lançado pelo modal ao carregar
		this.modal.on('shown.bs.modal', onModalShow.bind(this));		
		//Pegando o evento de fechamento do modal
		this.modal.on('hide.bs.modal', onModalClose.bind(this));
		//Chamado uma função através do click do botão salvar
		this.botaoSalvar.on('click', onBotaoSalvarClick.bind(this));
				
	}		
	//Colocando o focus no input do form
	function onModalShow() {
		this.inputNomeEstilo.focus();
	}
	
	//Deixando o valor do input vázio.
	function onModalClose(){
		this.inputNomeEstilo.val('');
		this.conteinerMensagemErro.addClass('hidden');
		this.form.find('.form-group').removeClass('has-error');
	}	
	
	function onBotaoSalvarClick() {		
		var nomeEstilo = this.inputNomeEstilo.val().trim();
		$.ajax({
			url:this.url,
			method: 'POST',
			contentType:'application/json',
			data: JSON.stringify({nome : nomeEstilo}),
			error: onErroSalvandoEstilo.bind(this),
			success: onEstiloSalvo.bind(this),
		});
	}
	
	function onErroSalvandoEstilo(obj){		
		var mensagemErro = obj.responseText;
		this.conteinerMensagemErro.removeClass('hidden');
		this.conteinerMensagemErro.html('<span>' + mensagemErro + '</span>');
		this.form.find('.form-group').addClass('has-error');
	}
	
	function onEstiloSalvo(estilo) {	
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		this.modal.modal('hide');
	}

	return EstiloCadastroRapido;
}());

$(function(){
	
	var estiloCadastroRapido = new Cerveja.EstiloCadastroRapido();
		estiloCadastroRapido.iniciar();
	
});
