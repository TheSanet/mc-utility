package org.jsanz.core.batch.fortressfinder.config;

public interface IFortressFinderSteps {
	
	public static final String STEP1 = "condiciones de controrno";

	public static final String STEP2 = "busqueda";
	
	public static final String STEP3 = "criba";
	
	
	/**
	 * Estados del paso 1 del proceso {@link SlimeFinderBatchConfiguration}
	 */
	public enum STEP1_STATUS {
		NADA_QUE_PROCESAR ("NADA_QUE_PROCESAR");

		private final String value;

		private STEP1_STATUS(String value) {
			this.value = value;
		}

		public String get() {
			return this.value;
		}
	}

}
