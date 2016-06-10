#!/bin/bash
cd AlinhamentoRNAseqDeNovo/
Trinity --seqType fq --max_memory 50G --left SRR030257_1.fastq --CPU 6 -q; echo 'terminou' >trini
