#!/bin/bash
cd AlinhamentoRNAseqDeNovo/
Trinity --seqType fq --max_memory 50G --left SRR030257_1_trimmed.fq --CPU 6 -q; echo 'terminou' >trini
