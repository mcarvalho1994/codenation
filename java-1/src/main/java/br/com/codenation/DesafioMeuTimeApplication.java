package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private List<Time> times = new ArrayList<>();
	private List<Jogador> jogadores = new ArrayList<>();

	@Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		if (id == null)
			throw new NullPointerException("O id não pode ser nulo");

		if (nome == null)
			throw new NullPointerException("O nome não pode ser nulo");

		if (dataCriacao == null)
			throw new NullPointerException("O dataCriacao não pode ser nulo");

		if (corUniformePrincipal == null)
			throw new NullPointerException("O corUniformePrincipal não pode ser nulo");

		if (corUniformeSecundario == null)
			throw new NullPointerException("O corUniformeSecundario não pode ser nulo");

		Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);

		verificaIdExistente(time, times);

		times.add(time);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		if (id == null)
			throw new NullPointerException("O id não pode ser nulo");

		if (idTime == null)
			throw new NullPointerException("O idTime não pode ser nulo");

		if (nome == null)
			throw new NullPointerException("O nome não pode ser nulo");

		if (dataNascimento == null)
			throw new NullPointerException("O dataNascimento não pode ser nulo");

		if (nivelHabilidade == null)
			throw new NullPointerException("O nivelHabilidade não pode ser nulo");

		if (salario == null)
			throw new NullPointerException("O salario não pode ser nulo");

		if (salario.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("O salario não pode ser < 0");

		if (nivelHabilidade < 0 || nivelHabilidade > 100)
			throw new IllegalArgumentException("O nivelHabilidade est� fora do intervalo [0, 100]");

		Jogador jogador = new Jogador(id, nome, idTime, dataNascimento, nivelHabilidade, salario);

		verificaIdExistente(jogador, jogadores);
		verificaTimeNaoExistente(idTime);
		jogadores.add(jogador);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
		verificaJogadorNaoExistente(idJogador);

		Jogador jogador = jogadores.get(jogadores.indexOf(new Identificador(idJogador)));
		jogador.setCapitao(true);

		jogadores.forEach(j -> {
			if (!jogador.getId().equals(j.getId()) && jogador.getIdTime().equals(j.getIdTime())) {
				j.setCapitao(false);
			}
		});
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
		verificaTimeNaoExistente(idTime);

		//Realiza um filtro no array jogadores, utilizando como parametros o id do time e o flag capitão
		//Caso não encontre nenhum resultado, o resultado será uma Exception CapitaoNaoInformadoException
		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime) && j.isCapitao()).mapToLong(j -> j.getId())
				.findAny().orElseThrow(CapitaoNaoInformadoException::new);
	}

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
		verificaJogadorNaoExistente(idJogador);

		Jogador jogador = jogadores.get(jogadores.indexOf(new Identificador(idJogador)));

		return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		verificaTimeNaoExistente(idTime);

		Time time = times.get(times.indexOf(new Identificador(idTime)));

		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		verificaTimeNaoExistente(idTime);

		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime)).map(j -> j.getId()).sorted()
				.collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
		verificaTimeNaoExistente(idTime);

		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
				.findFirst().map(Jogador::getId).get();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		verificaTimeNaoExistente(idTime);

		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId)).findFirst()
				.map(Jogador::getId).get();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
		return times.stream().map(Time::getId).sorted().collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
		verificaTimeNaoExistente(idTime);

		return jogadores.stream().filter(j -> j.getIdTime().equals(idTime))
				.sorted(Comparator.comparing(Jogador::getSalario).reversed().thenComparing(Jogador::getId)).findFirst()
				.map(Jogador::getId).get();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		verificaJogadorNaoExistente(idJogador);

		Jogador jogador = jogadores.get(jogadores.indexOf(new Identificador(idJogador)));
		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
		return jogadores.stream()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed()
				.thenComparing(Jogador::getId)).limit(top).map(Jogador::getId).collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		verificaTimeNaoExistente(timeDaCasa);
		verificaTimeNaoExistente(timeDeFora);

		Time timeCasa = times.get(times.indexOf(new Identificador(timeDaCasa)));
		Time timeFora = times.get(times.indexOf(new Identificador(timeDeFora)));

		return timeCasa.getCorUniformePrincipal().equals(timeFora.getCorUniformePrincipal())
				? timeFora.getCorUniformeSecundario()
				: timeFora.getCorUniformePrincipal();
	}

	private void verificaIdExistente(Identificador identificador, List<? extends Identificador> identificadores) {
		if (identificador == null)
			throw new NullPointerException("O identificador não pode ser nulo");

		if (identificador.getId() == null)
			throw new NullPointerException("O id não pode ser nulo");

		if (identificador.getId().compareTo(1L) < 0)
			throw new IllegalArgumentException("O id não pode ser <= 0");

		if (identificadores.contains(identificador))
			throw new IdentificadorUtilizadoException("O identificador já existe");
	}

	private void verificaIdNaoExistente(Identificador identificador, List<? extends Identificador> identificadores) {
		if (identificador == null)
			throw new NullPointerException("O identificador não pode ser nulo");

		if (identificador.getId() == null)
			throw new NullPointerException("O id não pode ser nulo");

		if (identificador.getId().compareTo(1L) < 0)
			throw new IllegalArgumentException("O id não pode ser <= 0");

		if (!identificadores.contains(identificador))
			throw new IdentificadorUtilizadoException("O identificador não existe");
	}

	private void verificaTimeNaoExistente(Long idTime) {
		if (idTime == null)
			throw new NullPointerException("O idTime não pode ser nulo");

		if (idTime.compareTo(1L) < 0)
			throw new IllegalArgumentException("O idTime não pode ser <= 0");

		try {
			verificaIdNaoExistente(new Identificador(idTime), times);
		} catch (IdentificadorUtilizadoException e) {
			throw new TimeNaoEncontradoException("O time não foi encontrado");
		}
	}

	private void verificaJogadorNaoExistente(Long idJogador) {
		if (idJogador == null)
			throw new NullPointerException("O idJogador não pode ser nulo");

		if (idJogador.compareTo(1L) < 0)
			throw new IllegalArgumentException("O idJogador não pode ser <= 0");

		try {
			verificaIdNaoExistente(new Identificador(idJogador), jogadores);
		} catch (IdentificadorUtilizadoException e) {
			throw new JogadorNaoEncontradoException("O jogador não foi encontrado");
		}
	}

}
